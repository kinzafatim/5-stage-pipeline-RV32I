// package single_cycle
// import chisel3._
// import chisel3.util
// import org.scalatest._
// import chiseltest._
// import chiseltest.experimental.TestOptionBuilder . _
// class Datamemtest extends FreeSpec with ChiselScalatestTester {
// "Data Mem Test" in {
//     test(new Datamem){ x =>
//     x.io.addr.poke(10.U)
//     x.io.writeData.poke(0.U)

//     x.io.memRead.poke(1.B)
//     x.io.memWrite.poke(0.B)

//     x.clock.step(10)

//     x.io.readData.expect(0.U)
//     }
// }
// }