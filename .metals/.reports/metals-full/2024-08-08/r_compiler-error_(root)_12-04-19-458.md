file://<WORKSPACE>/src/main/scala/pipeline/Top.scala
### scala.ScalaReflectionException: value D_E_ins is not a method

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.12.13
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-j8nJ_LvtRI-ktDvgVo55Ww== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.10.0/semanticdb-javac-0.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.12.13/scala-library-2.12.13.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/edu/berkeley/cs/chisel3_2.12/3.4.3/chisel3_2.12-3.4.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/edu/berkeley/cs/chisel3-macros_2.12/3.4.3/chisel3-macros_2.12-3.4.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/edu/berkeley/cs/chisel3-core_2.12/3.4.3/chisel3-core_2.12-3.4.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.12.13/scala-reflect-2.12.13.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/scopt/scopt_2.12/3.7.1/scopt_2.12-3.7.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/edu/berkeley/cs/firrtl_2.12/1.4.3/firrtl_2.12-1.4.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/antlr/antlr4-runtime/4.7.1/antlr4-runtime-4.7.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.9.0/protobuf-java-3.9.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/net/jcazevedo/moultingyaml_2.12/0.4.2/moultingyaml_2.12-0.4.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-native_2.12/3.6.9/json4s-native_2.12-3.6.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-text/1.8/commons-text-1.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/github/nscala-time/nscala-time_2.12/2.22.0/nscala-time_2.12-2.22.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/yaml/snakeyaml/1.26/snakeyaml-1.26.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-core_2.12/3.6.9/json4s-core_2.12-3.6.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.9/commons-lang3-3.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/joda-time/joda-time/2.10.1/joda-time-2.10.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/joda/joda-convert/2.2.0/joda-convert-2.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-ast_2.12/3.6.9/json4s-ast_2.12-3.6.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-scalap_2.12/3.6.9/json4s-scalap_2.12-3.6.9.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar [exists ]
Options:
-Xsource:2.11 -language:reflectiveCalls -deprecation -feature -Xcheckinit -Yrangepos -Xplugin-require:semanticdb


action parameters:
uri: file://<WORKSPACE>/src/main/scala/pipeline/Top.scala
text:
```scala
package pipeline
import chisel3._
import chisel3.util._
class Top extends Module {
  val io = IO(new Bundle {
    val in = Input(SInt(32.W))
    val out = Output(SInt(32.W))

  })
  io.out := io.in
  val fetch = Module(new IF("<WORKSPACE>/src/main/scala/pipeline/test.txt"))
  val decode = Module(new ID)
  val execute = Module(new EX)
  val memory = Module(new MA)
  val WriteBack = Module(new WB)

  // fetch and decode
  val F_D_ins = Reg(UInt(32.W))
  F_D_ins := fetch.io.instruction
  // := fetch.io.pc_out
  // := fetch.io.pc_4out
  fetch.io.addr :=
  fetch.io.pcsel :=
  fetch.io.aluout := 
  
  val D_E_ins = RegInit(0.U(32.W)) /



}
```



#### Error stacktrace:

```
scala.reflect.api.Symbols$SymbolApi.asMethod(Symbols.scala:240)
	scala.reflect.api.Symbols$SymbolApi.asMethod$(Symbols.scala:234)
	scala.reflect.internal.Symbols$SymbolContextApiImpl.asMethod(Symbols.scala:100)
	scala.tools.nsc.typechecker.ContextErrors$TyperContextErrors$TyperErrorGen$.MissingArgsForMethodTpeError(ContextErrors.scala:682)
	scala.tools.nsc.typechecker.Typers$Typer.cantAdapt$1(Typers.scala:913)
	scala.tools.nsc.typechecker.Typers$Typer.instantiateToMethodType$1(Typers.scala:944)
	scala.tools.nsc.typechecker.Typers$Typer.adapt(Typers.scala:1225)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5794)
	scala.tools.nsc.typechecker.Typers$Typer.typedDefDef(Typers.scala:5996)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5699)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5780)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:5844)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$6(Typers.scala:3290)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$6$adapted(Typers.scala:3287)
	scala.Option$WithFilter.foreach(Option.scala:407)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$4(Typers.scala:3287)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$4$adapted(Typers.scala:3285)
	scala.reflect.internal.Scopes$Scope.foreach(Scopes.scala:435)
	scala.tools.nsc.typechecker.Typers$Typer.addSynthetics$1(Typers.scala:3285)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3349)
	scala.tools.nsc.typechecker.Typers$Typer.typedTemplate(Typers.scala:2019)
	scala.tools.nsc.typechecker.Typers$Typer.typedClassDef(Typers.scala:1832)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5700)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5780)
	scala.tools.nsc.typechecker.Typers$Typer.typedStat$1(Typers.scala:5844)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typedStats$10(Typers.scala:3337)
	scala.tools.nsc.typechecker.Typers$Typer.typedStats(Typers.scala:3337)
	scala.tools.nsc.typechecker.Typers$Typer.typedPackageDef$1(Typers.scala:5410)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:5703)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:5780)
	scala.tools.nsc.typechecker.Analyzer$typerFactory$TyperPhase.apply(Analyzer.scala:116)
	scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:453)
	scala.tools.nsc.interactive.Global$TyperRun.$anonfun$applyPhase$1(Global.scala:1340)
	scala.tools.nsc.interactive.Global$TyperRun.applyPhase(Global.scala:1340)
	scala.tools.nsc.interactive.Global$TyperRun.typeCheck(Global.scala:1333)
	scala.tools.nsc.interactive.Global.typeCheck(Global.scala:665)
	scala.meta.internal.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:22)
	scala.meta.internal.pc.SimpleCollector.<init>(PcCollector.scala:335)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzycompute$1(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:19)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:73)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$semanticTokens$1(ScalaPresentationCompiler.scala:179)
```
#### Short summary: 

scala.ScalaReflectionException: value D_E_ins is not a method