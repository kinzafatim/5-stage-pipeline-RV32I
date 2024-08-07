package pipeline
import chisel3._
import chisel3.util._

class Top extends Module {
  val io = IO(new Bundle {
    val in = Input(SInt(32.W))
    val out = Output(SInt(32.W))

  })
  io.out := io.in
  val fetch = Module(new IF("/home/kinzaa/Desktop/5-stage-pipeline-RV32I/src/main/scala/pipeline/test.txt"))
  val decode = Module(new ID)
  val execute = Module(new EX)
  val memory = Module(new MA)
  val WriteBack = Module(new WB)

  // fetch and dcode
  val f_d_ins = Reg(UInt(32.W))
  f_d_ins:=fetch.io.instruction


}