using Laborator12_13.domain;
using Laborator12_13.Domain;
using Laborator12_13.Validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace Laborator12_13.Repository
{
    class NoteInFileRepository: InFileRepository<int, Inregistrare>
    {
        public NoteInFileRepository(IValidator<Inregistrare> vali, string fileName) : base(vali, fileName, EntityToFileMapping.CreateInregistrare)
        {
            loadFromFile();
        }
    }
}
