module control_unit(op_code, funct, memory_to_register, memory_to_write, branch, src_alu, register_dst, write_to_register, j_type, cnt_alu);
  input [5:0] op_code, funct;
  reg memory_to_register, memory_to_write, branch, src_alu, register_dst, write_to_register, j_type;
  reg [2:0] ctrl;
  reg [1:0] Op;
  output memory_to_register, memory_to_write, branch, src_alu, register_dst, write_to_register, j_type;
  output [2:0] cnt_alu;
  assign cnt_alu = ctrl;

  parameter additor = 6'b001000;
  parameter andi = 6'b001100;
  parameter bne = 6'b000101;
  parameter j = 6'b000010;
  parameter sw = 6'b101011;
  parameter jail = 6'b000011;
  parameter RType = 6'b000000;
  parameter lw = 6'b100011;
  parameter beq = 6'b000100;
 

  always@(*)begin
    case(op_code)
      RType:begin
        memory_to_register = 0;
        memory_to_write = 0;
        branch = 0;
        src_alu = 0;
        register_dst = 1;
        write_to_register = 1;
        Op = 2'b10;
        j_type = 0;
      end
      lw:begin
        memory_to_register = 1;
        memory_to_write = 0;
        branch = 0;
        src_alu = 1;
        register_dst = 0;
        write_to_register = 1;
        Op = 2'b00;
        j_type = 0;
      end
      sw:begin
        memory_to_write = 1;
        branch = 0;
        src_alu = 1;
        write_to_register = 0;
        Op = 2'b00;
        j_type = 0;
      end
      beq:begin
        memory_to_write = 0;
        branch = 1;
        src_alu = 0;
        write_to_register = 0;
        Op = 2'b01;
        j_type = 0;
      end
      additor:begin
        memory_to_register = 0;
        src_alu = 1;
        branch = 0;
        register_dst = 0;
        write_to_register = 1;
        Op = 2'b00;
        memory_to_write = 0;
        j_type = 0;
      end
      andi: begin
        src_alu = 1;
        branch = 0;
        register_dst = 0;
        memory_to_register = 0;
        write_to_register = 1;
        Op = 2'b11;
        memory_to_write = 0;
        j_type = 0;
       // ctrl = 3'b000;
      end 
      bne: begin
        //memory_to_register = 0;
        src_alu = 0;
        branch = 1;
       // register_dst = 0;
        write_to_register = 0;
        Op = 2'b01;
        memory_to_write = 0;
        j_type = 0;
      end 
      j: begin
        write_to_register = 0;
        branch = 0;
        memory_to_write = 0;
        j_type = 1;
      end
      jail: begin
        write_to_register = 1;
        branch = 0;
        memory_to_write = 0;
        j_type = 1;
      end
        endcase
        
    case(Op)
      2'b11:begin
        ctrl = 3'b000;
      end
      2'b00:begin
        ctrl = 3'b010;
      end
      2'b01:begin
        ctrl = 3'b110;
      end
      2'b10:begin
        case(funct)
          6'b100000:begin
            ctrl = 3'b010;
          end
          6'b100010:begin
            ctrl = 3'b110;
          end
          6'b100100:begin
            ctrl = 3'b000;
          end
          6'b100101:begin
            ctrl = 3'b001;
          end
          6'b101010:begin
            ctrl = 3'b111;
          end
          6'b001000:begin
            write_to_register = 0;
            branch = 0;
            memory_to_write = 0;
            j_type = 1;
          end
        endcase
      end
    endcase
  end

endmodule