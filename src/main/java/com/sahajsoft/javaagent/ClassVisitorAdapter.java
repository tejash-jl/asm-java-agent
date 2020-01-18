package com.sahajsoft.javaagent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassVisitorAdapter extends ClassVisitor {
    private String className;
    private String methodNameToIntercept;

    ClassVisitorAdapter(ClassVisitor cv, String className, String methodNameToIntercept) {
        super(Opcodes.ASM5, cv);
        this.className = className;
        this.methodNameToIntercept = methodNameToIntercept;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(methodNameToIntercept)) {
            return new MethodVisitorAdapter(mv, name, className);
        } else {
            return mv;
        }
    }

}
