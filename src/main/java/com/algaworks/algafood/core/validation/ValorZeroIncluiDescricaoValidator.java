package com.algaworks.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object  > {
    String valorField;
    String descricaoField;
    String descricaoObrigatoria;
    @Override
    public void initialize(ValorZeroIncluiDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
        boolean valido = true;
        // PEGANDO O VALOR DA TAXA FRETE
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.valorField).getReadMethod().invoke(objetoValidacao);
            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), this.descricaoField).getReadMethod().invoke(objetoValidacao);

            if (valor != null && valor.compareTo(BigDecimal.ZERO) == 0 && descricao!= null){
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }
        }
        catch (Exception e){
            throw new ValidationException();
        }


        return valido;
    }
}
