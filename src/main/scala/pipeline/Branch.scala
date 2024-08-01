package single_cycle
import chisel3._
import chisel3.util._
object branch{

  val beq   = 0.U(3.W)
  val bne   = 1.U(3.W)
  val blt   = 4.U(3.W)
  val bge    = 5.U(3.W)
  val bltu   = 6.U(3.W)
  val bgeu   = 7.U(3.W) 
}
import branch._
class LM_IO_Interface_BranchControl extends Bundle {
    val fnct3 = Input(UInt(3.W))
    val branch= Input(Bool())
    val x1 = Input(SInt(32.W))
    val x2 = Input(SInt(32.W))
    val br_taken = Output(Bool())
}
class Branch extends Module {
    val io = IO(new LM_IO_Interface_BranchControl)
    io.br_taken:=0.B
    switch(io.fnct3){
        is(beq){
            io.br_taken:= io.x1 === io.x2
        }
        is(bne){
                io.br_taken:= io.x1 =/= io.x2
        }
        is(blt){
                io.br_taken:= io.x1 < io.x2
        }
        is(bge){
                io.br_taken:= io.x1 >= io.x2
        }
        is(bltu){
                io.br_taken:= io.x1.asUInt < io.x2.asUInt
        }
        is(bgeu){
                io.br_taken:= io.x1.asUInt >= io.x2.asUInt
        }
    }
}
