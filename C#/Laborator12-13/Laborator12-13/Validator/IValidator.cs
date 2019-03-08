using System;
using System.Collections.Generic;
using System.Text;

namespace Laborator12_13.Validator
{
    public interface IValidator<E>
    {
        void Validate(E entity);
    }
}
