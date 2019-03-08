using System;
using System.Collections.Generic;
using System.Text;
using Laborator12_13.domain;

namespace Laborator12_13.Validator
{
    class ValidareTema: IValidator<Tema>
    {
        public void Validate(Tema entity)
        {
            if (entity.ID.Equals(null))
                throw new ValidationException("ID NULL");
            if (entity.ID < 0)
                throw new ValidationException("ID Negativ");
            if (entity.Descriere == null || entity.Descriere.Equals(""))
                throw new ValidationException("DESCRIERE NULL");
            if (entity.Deadline < 0)
                throw new ValidationException("DEADLINE NEGATIVA");
            if (entity.Deadline < 1 || entity.Deadline > 14)
                throw new ValidationException("DEADLINE INCORECT (ar trebui sa fie 1-14)");
            if (entity.Predare < 0)
                throw new ValidationException("PREDARE NEGATIVA");
            if (entity.Predare < 0 || entity.Predare > 14)
                throw new ValidationException("PREDARE INCORECT (ar trebui sa fie 1-14)");
            if (entity.Deadline < entity.Predare)
                throw new ValidationException("DEADLINE < PREDARE");
        }
    }
}

