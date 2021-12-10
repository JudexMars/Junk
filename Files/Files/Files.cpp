#include <iostream>
#include <fstream>

using namespace std;

int main()
{
    //ofstream outFile;
	fstream outFile;
	string fname = "text.txt";
    outFile.open(fname, ios::out);

	if (outFile.is_open())
	{
		outFile << "Hello there!" << endl;
		outFile << "Obiwan Kenobi..." << endl;
		outFile.close();
	}
	else {
		cout << "File cannot be created: " << fname << endl;
	}
}