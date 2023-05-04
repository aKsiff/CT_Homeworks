// Реализация логического вентиля NOT с помощью структурных примитивов
module NOT(in, out);
  // Входные порты помечаются как input, выходные как output
  input wire in;
  output wire out;
  // Ключевое слово wire для обозначения типа данных можно опустить,
  // тогда оно подставится неявно, например:
  /*
    input in;
    output out;
  */

  supply1 vdd; // Напряжение питания
  supply0 gnd; // Напряжение земли

  // p-канальный транзистор, сток = out, исток = vdd, затвор = in
  pmos pmos1(out, vdd, in); // (сток, исток, база)
  // n-канальный транзистор, сток = out, исток = gnd, затвор = in
  nmos nmos1(out, gnd, in);
endmodule

// Реализация NAND с помощью структурных примитивов
module NAND(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  supply0 gnd;
  supply1 pwr;

  // С помощью типа wire можно определять промежуточные провода для соединения элементов.
  // В данном случае nmos1_out соединяет сток транзистора nmos1 и исток транзистора nmos2.
  wire nmos1_out;

  // 2 p-канальных и 2 n-канальных транзистора
  pmos pmos1(out, pwr, in1);
  pmos pmos2(out, pwr, in2);
  nmos nmos1(nmos1_out, gnd, in1);
  nmos nmos2(out, nmos1_out, in2);
endmodule

// Реализация NOR с помощью структурных примитивов
module NOR(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  supply0 gnd;
  supply1 pwr;

  // Промежуточный провод, чтобы содединить сток pmos1 и исток pmos2
  wire pmos1_out;

  pmos pmos1(pmos1_out, pwr, in1);
  pmos pmos2(out, pmos1_out, in2);
  nmos nmos1(out, gnd, in1);
  nmos nmos2(out, gnd, in2);
endmodule

// Реализация AND с помощью NAND и NOT
module AND(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  // Промежуточный провод, чтобы передать выход вентиля NAND на вход вентилю NOT
  wire nand_out;

  // Схема для формулы AND(in1, in2) = NOT(NAND(in1, in2))
  NAND NAND1(in1, in2, nand_out);
  NOT NOT1(nand_out, out);
endmodule

// Реализация OR с помощью NOR и NOT
module OR(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  wire nor_out;

  // Схема для формулы OR(in1, in2) = NOT(NOR(in1, in2))
  NOR NOR1(in1, in2, nor_out);
  NOT NOT1(nor_out, out);
endmodule

// Реализация XOR с помощью NOT, AND, OR
module XOR(in1, in2, out);
  input wire in1;
  input wire in2;
  output wire out;

  wire not_in1;
  wire not_in2;

  wire and_out1;
  wire and_out2;

  wire or_out1;

  // Формула: XOR(in1, in2) = OR(AND(in1, NOT(in2)), AND(NOT(in1), in2))

  NOT NOT1(in1, not_in1);
  NOT NOT2(in2, not_in2);

  AND AND1(in1, not_in2, and_out1);
  AND AND2(not_in1, in2, and_out2);

  OR OR1(and_out1, and_out2, out);
endmodule


module IMPLICATE(in1, in2, out);
input wire in1;
input wire in2;
output wire out;

wire not1;

NOT NOT1(in1, not1);

OR OR1(not1, in2, out);


endmodule



module ternary_min(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  
  AND AND1(a[1], b[1], out[1]);
  wire or1, or2, or3, and2;

  OR OR1(a[0], a[1], or1);
  OR OR2(b[0], b[1], or2);
  OR OR3(a[0], b[0], or3);
  
  AND AND2(or1, or2, and2);
  AND AND3(and2, or3, out[0]);

endmodule

module ternary_max(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;

  wire or2, and1;

  OR OR1(a[1], b[1], out[1]);
  OR OR2(a[0], b[0], or2);
  AND AND1(out[1], or2, and1);
  XOR XOR1(and1, or2, out[0]);
endmodule

module ternary_any(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  
  wire or1;
  OR OR1(b[0], b[1], or1);
  AND AND1(a[1], or1, out[1]);

  wire and2, not_a0, not_a1, not_b1, not_b0, and3, and4, and5, and6, or2;

  AND AND2(a[0], b[0], and2);

  NOT NOT1(a[0], not_a0);
  NOT NOT2(a[1], not_a1);
  AND AND3(not_a0, not_a1, and3);
  AND AND4(b[1], and3, and4);

   NOT NOT3(b[0], not_b0);
   NOT NOT4(b[1], not_b1);
   AND AND5(not_b0, not_b1, and5);
   AND AND6(b[1], and5, and6);
   
   OR OR2(and2, and4, or2);
   OR OR3(or2, and6, out[0]);

endmodule


module ternary_consensus(a[1:0], b[1:0], out[1:0]);
  input [1:0] a;
  input [1:0] b;
  output [1:0] out;
  
  AND AND1(a[1], b[1], out[1]);
  wire imp1, imp2, n_imp1, n_imp2, xor1, or1;
  IMPLICATE IMPLICATE1(a[1], a[0], imp1);
  IMPLICATE IMPLICATE2(b[1], b[0], imp2);

  NOT NOT1(imp1, n_imp1);
  NOT NOT2(imp2, n_imp2);

  XOR XOR1(n_imp1, n_imp2, xor1);

  OR OR1(b[0], a[0], or1);
  OR OR2(or1, xor1, out[0]);

endmodule


module testbench();
  reg [1:0] a;
  reg [1:0] b;
  wire [1:0] out;

  //ternary_min my_min(a, b, out); //проверено
  //ternary_max my_max(a, b, out); //проверено
  //ternary_consensus my_consensus(a, b, out);//проверено
  ternary_any my_any(a, b, out);

initial begin
    $monitor("Input: ", a[1], a[0] , " ", b[1], b[0], " ", "Output: ", out[1], out[0]);
    #0  a[1] = 0; a[0] = 0; b[1] = 0; b[0] = 0;
    #5  a[1] = 0; a[0] = 0; b[1] = 0; b[0] = 1;
    #10 a[1] = 0; a[0] = 0; b[1] = 1; b[0] = 0;
    #15 a[1] = 0; a[0] = 1; b[1] = 0; b[0] = 0;
    #25 a[1] = 0; a[0] = 1; b[1] = 0; b[0] = 1;
    #35 a[1] = 0; a[0] = 1; b[1] = 1; b[0] = 0;
    #45 a[1] = 1; a[0] = 0; b[1] = 0; b[0] = 0;
    #55 a[1] = 1; a[0] = 0; b[1] = 0; b[0] = 1;
    #60 a[1] = 1; a[0] = 0; b[1] = 1; b[0] = 0;
  end
endmodule