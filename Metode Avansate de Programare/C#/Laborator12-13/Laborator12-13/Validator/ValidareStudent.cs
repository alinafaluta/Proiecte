using System;
using System.Collections.Generic;
using System.Text;
using Laborator12_13.domain;

namespace Laborator12_13.Validator
{
    class ValidareStudent : IValidator<Student>
    {
        

        public void Validate(Student entity)
        {
            if (entity.ID.Equals(null))
                throw new ValidationException("ID NULL");
            if (entity.ID < 0)
                throw new ValidationException("ID Negativ");
            if (entity.Email == null || entity.Email.Equals(""))
                throw new ValidationException("EMAIL NULL");
            if (entity.Grupa < 0)
                throw new ValidationException("GRUPA NEGATIVA");
            if (entity.Nume == null || entity.Nume.Equals(""))
                throw new ValidationException("NUME NULL");
            if (entity.Profesor == null || entity.Profesor.Equals(""))
                throw new ValidationException("PROFESOR NULL");
        }
    }
}
