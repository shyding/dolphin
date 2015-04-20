package com.freetmp.mbg.merge.declaration;

import com.freetmp.mbg.merge.AbstractMerger;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by pin on 2015/4/19.
 */
public class ConstructorDeclarationMerger extends AbstractMerger<ConstructorDeclaration> {

    private ConstructorDeclarationMerger(){}

    static {
        if(getMerger(ConstructorDeclaration.class) == null){
            register(ConstructorDeclaration.class,new ConstructorDeclarationMerger());
        }
    }

    @Override
    public ConstructorDeclaration merge(ConstructorDeclaration first, ConstructorDeclaration second) {

        ConstructorDeclaration cd = new ConstructorDeclaration();

        cd.setName(first.getName());
        cd.setJavaDoc(mergeSingle(first.getJavaDoc(), second.getJavaDoc()));
        cd.setComment(mergeSingle(first.getComment(), second.getComment()));
        cd.setModifiers(mergeModifiers(first.getModifiers(), second.getModifiers()));
        cd.setAnnotations(mergeCollections(first.getAnnotations(), second.getAnnotations()));
        cd.setParameters(mergeCollectionsInOrder(first.getParameters(), second.getParameters()));
        cd.setTypeParameters(mergeCollectionsInOrder(first.getTypeParameters(),second.getTypeParameters()));

        cd.setThrows(mergeListNoDuplicate(first.getThrows(),second.getThrows(),false));
        cd.setBlock(mergeSingle(first.getBlock(),second.getBlock()));
        return cd;
    }

    @Override
    public boolean isEquals(ConstructorDeclaration first, ConstructorDeclaration second) {
        if(first == second) return true;
        if(first == null || second == null) return false;

        if(!StringUtils.equals(first.getName(),second.getName())) return false;

        if(!isParametersEquals(first.getParameters(), second.getParameters())) return false;

        if(!isTypeParameterEquals(first.getTypeParameters(),second.getTypeParameters())) return false;

        return true;
    }
}
