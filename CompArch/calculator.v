module not_f(in, out);
  input wire in;
  output wire out;
  supply1 vdd; 
  supply0 gnd; 

  pmos pmos1(out, vdd, in);
  nmos nmos1(out, gnd, in);
endmodule

module and_f(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;
  wire nand_out;

  nAnd_f nand1(in1, in2, nand_out);
  not_f not1(nand_out, out);
endmodule

module or_f(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;
  wire nor_out;

  nOr_f nor1(in1, in2, nor_out);
  not_f not1(nor_out, out);
endmodule

module nAnd_f(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;
  supply0 gnd;
  supply1 pwr;
  wire nmos1_out;

  pmos pmos1(out, pwr, in1);
  pmos pmos2(out, pwr, in2);
  nmos nmos1(nmos1_out, gnd, in1);
  nmos nmos2(out, nmos1_out, in2);
endmodule

module nOr_f(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;
  supply0 gnd;
  supply1 pwr;
  wire pmos1_out;

  pmos pmos1(pmos1_out, pwr, in1);
  pmos pmos2(out, pmos1_out, in2);
  nmos nmos1(out, gnd, in1);
  nmos nmos2(out, gnd, in2);
endmodule

module xor_f(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;
  wire not_input1;
  wire not_input2;
  wire and_output1;
  wire and_output2;
  wire or_output1;

  not_f not1(in1, not_input1);
  not_f not2(in2, not_input2);
  and_f and_gate1(in1, not_input2, and_output1);
  and_f and_gate2(not_input1, in2, and_output2);
  or_f or_gate1(and_output1, and_output2, out);
endmodule

module tri_and_f(in1, in2, in3, out);
input wire in1, in2, in3;
output wire out;
wire mid;
and_f and_plate1(in1, in2, mid);
and_f and_plate2(mid, in3, out);

endmodule

module tri_or_f(in1, in2, in3, in4, out);
  input wire in1, in2, in3, in4; 
  output wire out;
  wire mid1, mid2;
  or_f or_plate1(in1, in2, mid1);
  or_f or_plate2(mid, in3, mid2);
  or_f or_plate3(mid2, in4, out);
endmodule

module maximum_out_of_two(in1, in2, logicIn, out);
    input wire in1, in2, logicIn;
    output wire out;
    wire not1;
    wire and1, and2;
    wire or1;
    not_f not_plate1(logicIn, not1);
    and_f and_plate1(in1, not1, and1);
    and_f and_plate2(in2, logicIn, and2);
    or_f or_plate1(and1, and2, out);
endmodule

module own_max(in1, in2, in3, in4, logic1, logic2, out);
input [3:0] in1, in2, in3, in4;
input logic1, logic2;
output [3:0] out;
wire notLog1, notLog0;
wire [3:0] mid1, mid2, mid3, mid4;

not_f not_plate1(logic1, notLog1);
not_f not_plate2(logic2, notLog2);

tri_and_f and_plates1(in1[0], notLog1, notLog2, mid1[0]);
tri_and_f and_plates2(in1[1], notLog1, notLog2, mid1[1]);
tri_and_f and_plates3(in1[2], notLog1, notLog2, mid1[2]);
tri_and_f and_plates4(in1[3], notLog1, notLog2, mid1[3]);

tri_and_f and_plates5(in2[0], logic1, notLog2, mid2[0]);
tri_and_f and_plates6(in2[1], logic1, notLog2, mid2[1]);
tri_and_f and_plates7(in2[2], logic1, notLog2, mid2[2]);
tri_and_f and_plates8(in2[3], logic1, notLog2, mid2[3]);

tri_and_f and_plates9(in3[0], notLog1, logic2, mid3[0]);
tri_and_f and_plates10(in3[1], notLog1, logic2, mid3[1]);
tri_and_f and_plates11(in3[2], notLog1, logic2, mid3[2]);
tri_and_f and_plates12(in3[3], notLog1, logic2, mid3[3]);

tri_and_f and_plates13(in4[0], logic1, logic2, mid4[0]);
tri_and_f and_plates14(in4[1], logic1, logic2, mid4[1]);
tri_and_f and_plates15(in4[2], logic1, logic2, mid4[2]);
tri_and_f and_plates16(in4[3], logic1, logic2, mid4[3]);

tri_or_f or_plate1(mid1[0], mid2[0], mid3[0], mid4[0], out[0]);
tri_or_f or_plate2(mid1[1], mid2[1], mid3[1], mid4[1], out[1]);
tri_or_f or_plate3(mid1[2], mid2[2], mid3[2], mid4[2], out[2]);
tri_or_f or_plate4(mid1[3], mid2[3], mid3[3], mid4[3], out[3]);

endmodule

module sum_out_of_two(in1, in2, logicIn, S, cOut);
input in1, in2, logicIn;
output S, cOut;
wire xor1, and1, and2;
wire and3, or1;
xor_f xor_plate1(in1, in2, xor1);
xor_f xor_plate2(xor1, logicIn, S);
and_f and_plate1(in2, logicIn, and1);
and_f and_plate2(in1, in2, and2);
and_f and_plate3(logicIn, in1, and3);
or_f or_plate1(and1, and2, or1);
or_f or_plate2(or1, and3, cOut);
endmodule

module inversion_vector_f(in, out);
  input wire [3:0] in;
  output wire [3:0] out;
  not_f not_plate1(in[0], out[0]);
  not_f not_plate2(in[1], out[1]);
  not_f not_plate3(in[2], out[2]);
  not_f not_plate4(in[3], out[3]);
  
endmodule

module tm_or_f(in1, in2, out); 
  input [3:0] in1, in2; 
  output [3:0] out; 
 
  or_f or_f1(in1[0], in2[0], out[0]); 
  or_f or_f2(in1[1], in2[1], out[1]); 
  or_f or_f3(in1[2], in2[2], out[2]); 
  or_f or_f4(in1[3], in2[3], out[3]); 
endmodule 
 
module tm_and_f(in1, logic1, logic2, out); 
  input [3:0] in1; 
  input logic1, logic2; 
  output [3:0] out; 
 
  wire and1, and2, and3, and4; 
 
  and_f and_f1(in1[0], logic1, and1); 
  and_f and_f2(in1[1], logic1, and2); 
  and_f and_f3(in1[2], logic1, and3); 
  and_f and_f4(in1[3], logic1, and4); 
  and_f and_f5(and1, logic2, out[0]); 
  and_f and_f6(and2, logic2, out[1]); 
  and_f and_f7(and3, logic2, out[2]); 
  and_f and_f8(and4, logic2, out[3]); 
endmodule

module decoder(in1[1:0], out[3:0]);
  input [1:0] in1;
  output [3:0] out;
  wire not1, not2;

  not_f not_plate1(in1[0], not1);
  not_f not_plate2(in1[1], not2);

  and_f and_plate1(not1, not2, out[0]);
  and_f and_plate2(in1[0], not2, out[1]);
  and_f and_plate3(not1, in1[1], out[2]);
  and_f and_plate4(in1[0], in1[1], out[3]);
endmodule

module regF(clk, d, we, out);
  input clk, we;
  input [3:0] d;
  output [3:0] out;

  d_latch d_latch1(clk, d[0], we, out[0]);
  d_latch d_latch2(clk, d[1], we, out[1]);
  d_latch d_latch3(clk, d[2], we, out[2]);
  d_latch d_latch4(clk, d[3], we, out[3]);
endmodule

module slt(a, b, c, out);
input [3:0] a, b, c;
output [3:0] out;
wire notA, notB, andOut1, andOut2, andOut3, andOut4, left, not1, and1, right;
not_f not_plate1(a[3], notA);
and_f and_plate1(notA, 1'b1, andOut1);
and_f and_plate2(b[3], 1'b1, andOut2);
and_f and_plate3(andOut1, andOut2, andOut3);
not_f not_plate2(andOut3, not1);
and_f and_plate4(c[3], 1'b1, andOut4);
and_f and_plate5(not1, andOut4, left);
and_f and_plate6(a[3], 1'b1, and1);
not_f not_plate3(b[3], notB);
and_f and_plate7(and1, notB, right);
or_f or_plate(left, right, out[0]);
and_f andGr1(a[0], 1'b0, out[1]);
and_f andGr3(a[0], 1'b0, out[2]);
and_f andGr2(a[0], 1'b0, out[3]);

endmodule

module alu(a, b, control, res);
input wire [3:0] a, b;
  input [2:0] control;
  output [3:0] res, sltOut;
  wire [3:0] bA, muxOut, andOut, orOut, result, cOut;
  inversion_vector_f notV1(b, bA);
  maximum_out_of_two mux_plate1(b[0], bA[0], control[2], muxOut[0]);
  maximum_out_of_two mux_plate2(b[1], bA[1], control[2], muxOut[1]);
  maximum_out_of_two mux_plate3(b[2], bA[2], control[2], muxOut[2]);
  maximum_out_of_two mux_plate4(b[3], bA[3], control[2], muxOut[3]);
  
  and_f and_plate1(a[0], muxOut[0], andOut[0]);
  and_f and_plate2(a[1], muxOut[1], andOut[1]);
  and_f and_plate3(a[2], muxOut[2], andOut[2]);
  and_f and_plate4(a[3], muxOut[3], andOut[3]);

  or_f or_plate1(a[0], muxOut[0], orOut[0]);
  or_f or_plate2(a[1], muxOut[1], orOut[1]);
  or_f or_plate3(a[2], muxOut[2], orOut[2]);
  or_f or_plate4(a[3], muxOut[3], orOut[3]);

  sum_out_of_two sum_plate1(a[0], muxOut[0], control[2], result[0], cOut[0]);
  sum_out_of_two sum_plate2(a[1], muxOut[1], cOut[0], result[1], cOut[1]);
  sum_out_of_two sum_plate3(a[2], muxOut[2], cOut[1], result[2], cOut[2]);
  sum_out_of_two sum_plate4(a[3], muxOut[3], cOut[2], result[3], cOut[3]);

  slt slt_plate1(a[3:0], b[3:0], res[3:0], sltOut);
  wire [3:0] mult1, mult2, mult3, mult4, mult5, mult6;
  wire not0, not1;
  not_f not_1(control[0], not0);
  not_f not_2(control[1], not1);
  own_max mux_out1(andOut, orOut, result, sltOut, control[0], control[1], res);
endmodule

module d_latch(clk, d, we, q);
  input clk;
  input d; 
  input we;

  output reg q;
  
  initial begin
    q <= 0;
  end
  
  always @ (negedge clk) begin
    if (we) begin
      q <= d;
    end
  end
endmodule

module register_file(clk, rd_addr, we_addr, we_data, rd_data);
  input clk; 
  input [1:0] rd_addr, we_addr;
  input [3:0] we_data; 

  output [3:0] rd_data; 
  wire not1, not2;  
  wire [3:0] dec;

  decoder dec_1(we_addr, dec);
  
  wire [3:0] l1, l2, l3, l4;

  regF regF_1(clk, we_data, dec[0], l1);
  regF regF_2(clk, we_data, dec[1], l2);
  regF regF_3(clk, we_data, dec[2], l3);
  regF regF_4(clk, we_data, dec[3], l4);

  wire [3:0] var1, var2, var3, var4, var5, var6;
  not_f not_1(rd_addr[0], not1);
  not_f not_2(rd_addr[1], not2);
  tm_and_f and_1(l1, not1, not2, var1);
  tm_and_f and_2(l2, rd_addr[0], not2, var2);
  tm_and_f and_3(l3, not1, rd_addr[1], var3);
  tm_and_f and_4(l4, rd_addr[0], rd_addr[1], var4);

  tm_or_f or1(var1, var2, var5);
  tm_or_f or2(var3, var5, var6);
  tm_or_f or3(var4, var6, rd_data); 
endmodule

module calculator(clk, rd_addr, immediate, we_addr, control, rd_data);
  input clk;
  input [1:0] rd_addr;
  input [3:0] immediate; 
  input [1:0] we_addr; 
  input [2:0] control; 

  output [3:0] rd_data; 

  wire [3:0] r1;

  register_file reg_file1(clk,rd_addr, we_addr, reg1, rd_data);
  alu alu1(rd_data, immdeiate, control, r1);

endmodule

module alu_test();
    reg [3:0] a, b;
    reg [2:0] control;
    wire [3:0] out;
    alu alu1(a, b, control, out);
    reg [3:0] slt = 0;
 
    reg[32:0] i, j;
 
    initial begin
        for (i = 0; i <= 15 ; i++) begin
          a = i[3:0];
 
          for (j = 0; j <= 15 ; j++) begin
            b = j[3:0];
 
            // and
            control = 3'b000;
            #1;
            if((a & b) != out)
              $display("and %b %b = %b", a, b, out);
 
            // or
            control = 3'b001;
            #1;
            if((a | b) != out)
              $display("or %b %b = %b", a, b, out);
 
            // sum
            control = 3'b010;
            #1;
            if((a + b) != out)
              $display("sum %b %b = %b", a, b, out);
 
            // and not
            control = 3'b100;
            #1;
            if((a & ~b) != out)
              $display("and not %b %b = %b", a, b, out);
 
            // or not
            control = 3'b101;
            #1;
            if((a | ~b) != out)
              $display("or not %b %b = %b", a, b, out);
 
            // sub
            control = 3'b110;
            #1;
            if((a - b) != out)
              $display("sub %b %b = %b", a, b, out);
 
            // slt
            control = 3'b111;
            slt = a + (~b + 1);
            #1;
            if({3'b000, slt[3]} != out)
              $display("slt %b %b = %b", a, b, out);
          end
        end
    end
endmodule

module calculator_test();

  reg [1:0] rd_addr, we_addr;
  reg [2:0] control;
  reg signed [3:0] immediate;
  wire signed [3:0] rd_data;
  reg clk;
 
 
  reg[2:0] AND = 3'b000;
  reg[2:0] OR = 3'b001;
  reg[2:0] SUM = 3'b010;
  reg[2:0] AND_NOT = 3'b100;
  reg[2:0] OR_NOT = 3'b101;
  reg[2:0] SUB = 3'b110;
  reg[2:0] SLT = 3'b111;
 
  calculator calc(clk, rd_addr, immediate, we_addr, control, rd_data);
 
  `define calc(operation, im) \
        #5; \
        clk = 1; \
        control = operation; \
        immediate = im; \
        rd_addr = 2'b00; \
        we_addr = 2'b00; \
        #5; \
        clk = 0;
 
  initial begin
    $monitor("rd_data =%d = %b", rd_data, rd_data);
    `calc(SUM, 2);
    `calc(SUB, 1);
    `calc(OR, 4'b1010);
  end
endmodule