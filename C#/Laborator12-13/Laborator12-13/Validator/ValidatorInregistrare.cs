using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using Laborator12_13.domain;

namespace Laborator12_13.Validator
{
    public class ValidatorNota : IValidator<Inregistrare>
    {
        public void Validate(Inregistrare entity)
        {
            if (entity.Id.Equals(null))
                throw new ValidationException("ID NULL");
            if (entity.ID < 0)
                throw new ValidationException("ID Negativ");
            if (entity.IdStudent < 0)
                throw new ValidationException("ID Student Negativ");
            if (entity.IdTema < 0)
                throw new ValidationException("ID Tema Negativ");
            if (entity.Nota < 0 || entity.Nota > 10)
                throw new ValidationException("Nota incorecta");
        }
    }
}
