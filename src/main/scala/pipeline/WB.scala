package pipeline
import chisel3._
import chisel3.util._
class WB extends Module {
  val io = IO(new Bundle {
    val in = Input(SInt(32.W))
    val out = Output(SInt(32.W))

  })
}
