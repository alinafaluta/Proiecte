#include "validator.h"

int validator(Oferta of)
{
	if (of.pret <= 0 || of.suprafata <= 0) return NUMAR_INVALID;
	if (!strlen(of.adresa) || !strlen(of.tip)) return STRING_VID;
	if (strcmp(of.tip, "casa") && strcmp(of.tip, "apartament") && strcmp(of.tip, "teren")) return TIP_INVALID;
	return SUCCES;
}

void testValidator()
{
	Oferta of = creeareOferta(0, 10, "adresa_validator", "tip_validator");
	assert(validator(of) == NUMAR_INVALID);
	Oferta of1 = creeareOferta(0, -1, "adresa_validator", "tip_validator");
	assert(validator(of1) == NUMAR_INVALID);
	Oferta of2 = creeareOferta(100, 10, "", "tip_validator");
	assert(validator(of2) == STRING_VID);
	Oferta of3 = creeareOferta(100, 10, "adresa_validator", "tip_validator");
	assert(validator(of3) == TIP_INVALID);
	Oferta of4 = creeareOferta(100, 10, "adresa_validator", "casa");
	assert(validator(of4) == SUCCES);
}
