module register_file(clk, we3, a1, a2, a3, wd3, rd1, rd2);
  // we3 to a3, clk - sync signal
  input clk, we3;
  // read from a1, a2, write to a3
  input [4:0] a1, a2, a3;
  input [31:0] wd3;
  // data from a1 and a2
  output [31:0] rd1, rd2;
  reg [31:0] mem[0:31];

  // filled with 0
  integer i;
  initial begin
    for (i = 0; i < 32; i = i + 1) begin
      mem[i] = 0;
   	end
  end

  // reading
  assign rd1 = mem[a1];
  assign rd2 = mem[a2];

  always @ (posedge clk) begin
    // if we3 = 1
    if (we3) mem[a3] = wd3;
  end
endmodule