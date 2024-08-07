package single_cycle
import chisel3._
import chisel3.util._

class RV32I extends Module {
  val io = IO(new Bundle {
    val in = Input(SInt(32.W))
    val out = Output(SInt(32.W))

  })
  io.out := io.in
  val fetch = Module(new IF)
  val decode = Module(new ID)
  val execute = Module(new EX)
  val memory = Module(new MA)
  val WriteBack = Module(new WB)
}