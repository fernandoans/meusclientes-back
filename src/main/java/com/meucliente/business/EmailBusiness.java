package com.meucliente.business;

import com.meucliente.business.enums.CodBusinessEmail;
import com.meucliente.dto.EmailDTO;

public class EmailBusiness {
  
  private EmailBusiness() {
  }

  public static CodBusinessEmail verificar(EmailDTO email) {
    if (FuncoesBusiness.naoContemValor(email.getDescricao())) {
      return CodBusinessEmail.EMAIL_NAO_INFORMADO;
    }
    if (!FuncoesBusiness.isValidarEmail(email.getDescricao())) {
      return CodBusinessEmail.EMAIL_MALFORMADO;
    }
    return CodBusinessEmail.OK;
  }
}