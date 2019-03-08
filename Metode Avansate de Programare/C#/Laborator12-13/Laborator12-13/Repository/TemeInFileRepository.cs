using Laborator12_13.domain;
using Laborator12_13.Domain;
using Laborator12_13.Validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace Laborator12_13.Repository
{
    class TemeInFileRepository: InFileRepository<int, Tema>
    {
        public TemeInFileRepository(IValidator<Tema> vali, string fileName) : base(vali, fileName, EntityToFileMapping.CreateTema)
        {
            loadFromFile();
        }
    }
}
