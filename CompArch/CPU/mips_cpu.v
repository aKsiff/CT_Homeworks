`include "alu.v"
`include "control_unit.v"
`include "util.v"


module mips_cpu(clk, curr_pc, pc_new, instruction_memory_a, instruction_memory_rd, data_memory_a, data_memory_rd, data_memory_we, data_memory_wd,
register_a1, register_a2, register_a3, register_we3, register_wd3, register_rd1, register_rd2);
input clk;
inout [31:0] curr_pc;
output wire PCSrc; // to chose from multiplex
output wire [31:0] summator; // to tranfer to next command
output wire [31:0] first_adder;
output wire [31:0] aftermux;
output memtoreg, memwrite, branch, alusrc, regdst, regwrite, zero, j_clutch; // flags
inout [2:0] alucontrol;
wire [31:0] alures;
wire [31:0] for_sing;
wire [31:0] alu2nd;
wire [31:0] opa; 
wire [31:0] sign_lmm; 
wire [31:0] sign_lmm2;
wire [31:0] sing_const;
wire [31:0] for_ans; // ans from 1 and 2
wire [31:0] after_sdvig; // after shift
output [31:0] pc_new;
output data_memory_we;
output [31:0] instruction_memory_a, data_memory_a, data_memory_wd;
// data from reading
inout [31:0] instruction_memory_rd, data_memory_rd;
// we3 for reg file
output register_we3;
// номера регистров
output [4:0] register_a1, register_a2, register_a3;
// data for writing
output [31:0] register_wd3;
// data from reg f
inout [31:0] register_rd1, register_rd2;

assign instruction_memory_a = curr_pc; 

control_unit my_ctrlunit(instruction_memory_rd [31:26], instruction_memory_rd [5:0], memtoreg, memwrite, branch, alusrc, regdst, regwrite, j_clutch, alucontrol);

adder final_adder(curr_pc, 4, summator); // curr_pc + 4


assign register_a1 = instruction_memory_rd [25:21]; // а1
assign register_a2 = instruction_memory_rd [20:16]; // а2
assign register_we3 = regwrite; // flag

assign register_a3 = j_clutch ? 31 : (regdst ? instruction_memory_rd[15:11] : register_a2);

sign_extend xxx(instruction_memory_rd [15:0], sign_lmm); 

sign_extend_mod sem(instruction_memory_rd [25:0], sing_const); // enlarge const from 26 to 32 for j

//need shift
shl_2 shl(sign_lmm, sign_lmm2); // shift from 16 to 32
shl_2 vbn(sing_const, after_sdvig);

mux2_32 my_mux2_32(register_rd2, sign_lmm, alusrc, alu2nd);

assign opa = sign_lmm2; // 
assign for_sing = after_sdvig;

alu my_alu(register_rd1, alu2nd, alucontrol, alures, zero);

assign data_memory_we = memwrite;

reg buffer;
assign PCSrc = buffer; 

always@(*)begin
if (instruction_memory_rd [31:26] != 6'b000101)
begin
    buffer = zero & branch;
end
else 
begin
    buffer = (!zero) & branch;
end
end

assign data_memory_a = alures;
assign data_memory_wd = register_rd2;

mux2_32 my_mux32_2(alures, data_memory_rd, memtoreg, aftermux); // Result goes to wd3

assign register_wd3 = j_clutch ? curr_pc + 4 : aftermux;

adder ok(opa, summator, first_adder);
assign key = j_clutch;
mux2_32 m(summator, first_adder, PCSrc, for_ans); 
assign pc_new = instruction_memory_rd [31:26] == 0 && instruction_memory_rd [5:0] == 6'b001000 ? register_rd1 : (key ? for_sing : for_ans);

initial begin
    $dumpfile("./dump.vcd");
    $dumpvars;
end

endmodule
