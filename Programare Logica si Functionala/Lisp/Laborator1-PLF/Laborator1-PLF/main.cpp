#include "lista.h"
#include <iostream>
#include <string>

int main()
{
	Lista l;
	l = creare();
	tipar(l);
	verifMultime(l);
	verifDistincte(l);

}