package com.sahajsoft.javaagent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ASMTransformer implements ClassFileTransformer {

    private String classNameToTransform;
    private String methodNameToIntercept;

    ASMTransformer(String classNameToTransform, String methodNameToIntercept) {
        this.classNameToTransform = classNameToTransform;
        this.methodNameToIntercept = methodNameToIntercept;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.equals(classNameToTransform)) {
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor visitor = new ClassVisitorAdapter(writer, className, methodNameToIntercept);
            reader.accept(visitor, 0);
            return writer.toByteArray();
        }

        return null;
    }

}
