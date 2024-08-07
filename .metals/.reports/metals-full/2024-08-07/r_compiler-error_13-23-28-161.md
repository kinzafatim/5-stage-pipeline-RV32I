file://<WORKSPACE>/src/test/scala/pipeline/IFtest.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 527
uri: file://<WORKSPACE>/src/test/scala/pipeline/IFtest.scala
text:
```scala
package pipeline
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._

import chiseltest.experimental.TestOptionBuilder . _

class IFtest extends FreeSpec with ChiselScalatestTester {
"Instruction Fetch Test" in{
    test(new IF("./src/main/scala/pipeline/test.txt")){ x =>
        // x.io.pcsel.poke()
        // x.io.aluout.poke()
        
        x.io.addr.poke(24.U)
        
        x.clock.step(10)

        x.io.instruction.expect(0.U)
        x.io.pc_out.expect()
        x.io.pc_4out.expect(@@)
    }}
    }
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0