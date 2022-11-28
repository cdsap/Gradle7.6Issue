# Gradle 7.6 issue using CompileStatic annotation

## Gradle 7.5.1
Task `:compileGroovy` works with:
```
@CompileStatic
class Foo {
    void apply(Project project) {
        project.tasks.withType(MergeSourceSetFolders).configureEach { Task task ->
            task.outputs.doNotCacheIf("", { true })
        }
    }
}
```

## Gradle 7.6
Task `:compileGroovy` with the same code:
```
@CompileStatic
class Foo {
    void apply(Project project) {
        project.tasks.withType(MergeSourceSetFolders).configureEach { Task task ->
            task.outputs.doNotCacheIf("", { true })
        }
    }
}
```

Fails with:
```
Caused by: java.lang.NoClassDefFoundError: com.android.ide.common.resources.AssetSet
        at org.codehaus.groovy.ast.decompiled.AsmReferenceResolver.resolveClass(AsmReferenceResolver.java:46)
        at org.codehaus.groovy.ast.decompiled.TypeSignatureParser.visitEnd(TypeSignatureParser.java:102)
        at groovyjarjarasm.asm.signature.SignatureReader.parseType(SignatureReader.java:206)
        at groovyjarjarasm.asm.signature.SignatureReader.parseType(SignatureReader.java:240)
        at groovyjarjarasm.asm.signature.SignatureReader.accept(SignatureReader.java:114)
        at org.codehaus.groovy.ast.decompiled.MemberSignatureParser.createMethodNode(MemberSignatureParser.java:98)
        at org.codehaus.groovy.ast.decompiled.DecompiledClassNode.lambda$createMethodNode$1(DecompiledClassNode.java:233)
        at org.codehaus.groovy.ast.decompiled.DecompiledClassNode.createMethodNode(DecompiledClassNode.java:239)
        at org.codehaus.groovy.ast.decompiled.DecompiledClassNode.lazyInitMembers(DecompiledClassNode.java:206)
        at org.codehaus.groovy.ast.decompiled.DecompiledClassNode.getDeclaredField(DecompiledClassNode.java:116)
        at org.codehaus.groovy.ast.ClassNode.getDeclaredField(ClassNode.java:714)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.existsProperty(StaticTypeCheckingVisitor.java:1543)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.existsProperty(StaticCompilationVisitor.java:557)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.existsProperty(StaticTypeCheckingVisitor.java:1466)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitPropertyExpression(StaticTypeCheckingVisitor.java:699)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitPropertyExpression(StaticCompilationVisitor.java:571)
        at org.codehaus.groovy.ast.expr.PropertyExpression.visit(PropertyExpression.java:63)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitMethodCallExpression(StaticTypeCheckingVisitor.java:3356)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitMethodCallExpression(StaticCompilationVisitor.java:421)
        at org.codehaus.groovy.ast.expr.MethodCallExpression.visit(MethodCallExpression.java:76)
        at org.codehaus.groovy.ast.CodeVisitorSupport.visitExpressionStatement(CodeVisitorSupport.java:117)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitExpressionStatement(ClassCodeVisitorSupport.java:200)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitExpressionStatement(StaticTypeCheckingVisitor.java:2190)
        at org.codehaus.groovy.ast.stmt.ExpressionStatement.visit(ExpressionStatement.java:40)
        at org.codehaus.groovy.ast.CodeVisitorSupport.visitBlockStatement(CodeVisitorSupport.java:86)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitBlockStatement(ClassCodeVisitorSupport.java:164)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitBlockStatement(StaticTypeCheckingVisitor.java:3999)
        at org.codehaus.groovy.ast.stmt.BlockStatement.visit(BlockStatement.java:69)
        at org.codehaus.groovy.ast.CodeVisitorSupport.visitClosureExpression(CodeVisitorSupport.java:239)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitClosureExpression(StaticTypeCheckingVisitor.java:2425)
        at org.codehaus.groovy.ast.expr.ClosureExpression.visit(ClosureExpression.java:46)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitMethodCallArguments(StaticTypeCheckingVisitor.java:2808)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitMethodCallExpression(StaticTypeCheckingVisitor.java:3606)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitMethodCallExpression(StaticCompilationVisitor.java:421)
        at org.codehaus.groovy.ast.expr.MethodCallExpression.visit(MethodCallExpression.java:76)
        at org.codehaus.groovy.ast.CodeVisitorSupport.visitExpressionStatement(CodeVisitorSupport.java:117)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitExpressionStatement(ClassCodeVisitorSupport.java:200)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitExpressionStatement(StaticTypeCheckingVisitor.java:2190)
        at org.codehaus.groovy.ast.stmt.ExpressionStatement.visit(ExpressionStatement.java:40)
        at org.codehaus.groovy.ast.CodeVisitorSupport.visitBlockStatement(CodeVisitorSupport.java:86)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitBlockStatement(ClassCodeVisitorSupport.java:164)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitBlockStatement(StaticTypeCheckingVisitor.java:3999)
        at org.codehaus.groovy.ast.stmt.BlockStatement.visit(BlockStatement.java:69)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitClassCodeContainer(ClassCodeVisitorSupport.java:138)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitConstructorOrMethod(ClassCodeVisitorSupport.java:111)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitConstructorOrMethod(StaticTypeCheckingVisitor.java:2179)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitMethod(ClassCodeVisitorSupport.java:106)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.startMethodInference(StaticTypeCheckingVisitor.java:2633)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitMethod(StaticTypeCheckingVisitor.java:2601)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitConstructorOrMethod(StaticCompilationVisitor.java:236)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitMethod(StaticCompilationVisitor.java:251)
        at org.codehaus.groovy.ast.ClassNode.visitMethods(ClassNode.java:1094)
        at org.codehaus.groovy.ast.ClassNode.visitContents(ClassNode.java:1087)
        at org.codehaus.groovy.ast.ClassCodeVisitorSupport.visitClass(ClassCodeVisitorSupport.java:52)
        at org.codehaus.groovy.transform.stc.StaticTypeCheckingVisitor.visitClass(StaticTypeCheckingVisitor.java:417)
        at org.codehaus.groovy.transform.sc.StaticCompilationVisitor.visitClass(StaticCompilationVisitor.java:197)
        at org.codehaus.groovy.transform.sc.StaticCompileTransformation.visit(StaticCompileTransformation.java:67)
        at org.codehaus.groovy.transform.ASTTransformationVisitor.visitClass(ASTTransformationVisitor.java:143)
        at org.codehaus.groovy.transform.ASTTransformationVisitor.lambda$addPhaseOperations$2(ASTTransformationVisitor.java:221)
        at org.codehaus.groovy.control.CompilationUnit$IPrimaryClassNodeOperation.doPhaseOperation(CompilationUnit.java:942)
        ... 34 more
```

Removing the annotation `@CompileStatic` works.
