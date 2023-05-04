module data_memory(a, we, clk, wd, result_data);
  // we - flag, clk - sync signal
  input we, clk;
  // adress
  input [31:0] a;
  // data
  input [31:0] wd;
  // res
  output [31:0] result_data;

  // 2048 cells with 32 bits
  reg [31:0] ram[0:2047];

  // 0
  integer i;
  initial begin
    for (i = 0; i < 2048; i = i + 1) begin
      ram[i] = 0;
   	end
  end

  assign result_data = ram[a / 4];

  // write on front
  always @ (posedge clk) begin
    // if we = 1
    if (we) ram[a / 4] = wd;
  end
endmodule

module instruction_memory(a, result_data);
  // adress
  input [31:0] a;
  output [31:0] result_data;
  // непосредственно память, 64 ячейки по 32 бита
  reg [31:0] ram[0:63];

  // память заполняется двоичными данными из
  // файла intructions.dat
  initial begin
    $readmemb("instructions.dat", ram);
  end

  // чтение происходит из элемента массива по a / 4, т.к.
  // адресация побайтовая, а ячейки массива по 32 бита (4 байта)
  assign result_data = ram[a / 4];
endmodule