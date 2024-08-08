// package pipeline
// import chisel3._
// import chisel3.util._

// class Top extends Module {
//   val io = IO(new Bundle {
//     val in = Input(SInt(32.W))  // Example input
//     val out = Output(SInt(32.W)) // Example output
//   })

//   // Instantiate all pipeline stages
//   val fetch = Module(new IF("/path/to/your/instruction/mem/file"))
//   val decode = Module(new ID)
//   val execute = Module(new EX)
//   val memory = Module(new MA)
//   val writeBack = Module(new WB)

//   // Define pipeline registers between each stage
//   val F_D_ins = RegInit(0.U(32.W)) // IF/ID pipeline register
//   val D_E_ins = RegInit(0.U(32.W)) // ID/EX pipeline register
//   val E_M_ins = RegInit(0.U(32.W)) // EX/MEM pipeline register
//   val M_W_ins = RegInit(0.U(32.W)) // MEM/WB pipeline register

//   // IF Stage
//   fetch.io.pcsel := false.B // Example control signal, modify as needed
//   fetch.io.aluout := 0.U // Example ALU output, modify as needed
//   fetch.io.addr := 0.U // Example address, modify as needed
//   F_D_ins := fetch.io.instruction

//   // ID Stage
//   decode.io.instruction := F_D_ins
//   D_E_ins := decode.io.out // Connect appropriate decode outputs to the next stage

//   // EX Stage
//   execute.io.fnct3 := decode.io.fnct3
//   execute.io.branch := decode.io.branch
//   execute.io.x1 := decode.io.x1
//   execute.io.x2 := decode.io.x2
//   execute.io.in_A := decode.io.in_A
//   execute.io.in_B := decode.io.in_B
//   execute.io.alu_Op := decode.io.alu_Op
//   E_M_ins := execute.io.out // Connect appropriate execute outputs to the next stage

//   // MA Stage
//   memory.io.addr := execute.io.out.asUInt // Example connection, modify as needed
//   memory.io.writeData := decode.io.in_B // Example connection, modify as needed
//   memory.io.memRead := decode.io.memRead
//   memory.io.memWrite := decode.io.memWrite
//   M_W_ins := memory.io.readData

//   // WB Stage
//   writeBack.io.in := M_W_ins
//   io.out := writeBack.io.out // Example connection to top module output

//   // Example of connecting the pipeline register outputs to the next stage inputs
//   // Modify these connections based on your exact module definitions and requirements
// }
