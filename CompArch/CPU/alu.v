module alu(src_a, src_b, alu_cnt, alu_result, zero);
  input signed [31:0] src_a, src_b;
  input [2:0] alu_cnt;
  output [31:0] alu_result;
  output zero;
  reg [31:0] alu_res;
  assign alu_result = alu_res;

  parameter ADD = 3'b010;
  parameter SUB = 3'b110;
  parameter OR = 3'b001;
  parameter AND = 3'b000;
  parameter SLT = 3'b111;


  always@(*)begin
    case(alu_cnt)
      ADD:begin
        alu_res = src_a + src_b;
      end
      SUB:begin
        alu_res = src_a - src_b;
      end
      OR:begin
        alu_res = src_a | src_b;
      end
      AND:begin
        alu_res = src_a & src_b;
      end
      SLT:begin
        if (src_a < src_b) begin
         alu_res = 32'b1;
          end
        else begin
          alu_res = 32'b0;
        end
      end
    endcase
  end

  assign zero = alu_res == 0;

endmodule