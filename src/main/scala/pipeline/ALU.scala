package single_cycle
import chisel3._
import chisel3.util._

object alu_op {
  val alu_add = 0.U(4.W)
  val alu_sub = 1.U(4.W)
  val alu_sll = 2.U(4.W)
  val alu_slt = 4.U(4.W)
  val alu_sltu = 6.U(4.W)
  val alu_xor = 8.U(4.W)
  val alu_srl = 10.U(4.W)
  val alu_sra = 11.U(4.W)
  val alu_or = 12.U(4.W)
  val alu_and = 14.U(4.W)
}

import alu_op._

class ALU extends Module {
  val io = IO(new Bundle{
    val in_A = Input(SInt(32.W))
    val in_B = Input(SInt(32.W))
    val alu_Op = Input(UInt(4.W))
    val out = Output(SInt(32.W))
    val sum = Output(SInt(32.W))
  })
  // add, sub 
  val sum = io.in_A + Mux(io.alu_Op(0), (-io.in_B), io.in_B)
  // Comparison for SLT and SLTU instructionuctions
  val cmp = Mux(io.in_A(31) === io.in_B(31), sum(31), 
                Mux(io.alu_Op(1), io.in_B(31), io.in_A(31)))
  // Shift amount
  val shamt = io.in_B(4, 0)
  val shin = Mux(io.alu_Op(3), io.in_A.asUInt, Reverse(io.in_A.asUInt))
  val shiftr = (Cat(io.alu_Op(0) && shin(31), shin) >> shamt)(31, 0)
  val shiftl = Reverse(shiftr)
  // Default output values
  io.out := 0.S
  io.sum := 0.S

  // ALU operation logic
  switch(io.alu_Op) {
    is(alu_add) {
      io.out := sum
    }
    is(alu_sub) {
      io.out := sum
    }
    is(alu_slt) {
      io.out := cmp.asSInt()
    }
    is(alu_sltu) {
      io.out := cmp.asSInt()
    }
    is(alu_sra) {
      io.out := shiftr.asSInt()
    }
    is(alu_srl) {
      io.out := shiftr.asSInt()
    }
    is(alu_sll) {
      io.out := shiftl.asSInt()
    }
    is(alu_and) {
      io.out := (io.in_A & io.in_B)
    }
    is(alu_or) {
      io.out := (io.in_A | io.in_B)
    }
    is(alu_xor) {
      io.out := (io.in_A ^ io.in_B)
    }
  }
  io.sum := sum
}