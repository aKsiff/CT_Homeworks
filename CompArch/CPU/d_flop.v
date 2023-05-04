module d_flop(d, clk, q);
  // data tranfered to register
  input [31:0] d;
  // sync sigmal
  input clk;

  // reg
  output reg [31:0] q;

  // filled with 0
  initial begin
    q <= 32'b0;
  end
  always @ (posedge clk)
    q <= d;
endmodule