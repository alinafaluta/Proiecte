
#include "Persoana.h"
#include "Validare.h"

int validare(Persoana p)
{
	if (!strlen(p.nume)) return NUME_VID;
	if (!strlen(p.prenume)) return PRENUME_VID;
	if (p.scor < 0 || p.scor>100) return SCOR_INVALID;
	return SUCCES;
}

