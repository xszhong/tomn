	lemma=School	O=1	pos=NNP	lemma[1]=start	O[1]=1	pos[1]=VBZ	lemma[2]=next	M[2]=1	pos[2]=JJ	__BOS__
	lemma=start	O=1	pos=VBZ	lemma[-1]=School	O[-1]=1	pos[-1]=NNP	lemma[1]=next	M[1]=1	pos[1]=JJ	lemma[2]=week	T[2]=1	pos[2]=NN
	lemma=next	M=1	RECORD=1	pos=JJ	lemma[-1]=start	O[-1]=1	pos[-1]=VBZ	lemma[1]=week	T[1]=1	pos[1]=NN	lemma[-2]=School	O[-2]=1	pos[-2]=NNP	lemma[2]=holiday	O[2]=1	pos[2]=NNS
	lemma=week	T=1	pos=NN	lemma[-1]=next	M[-1]=1	pos[-1]=JJ	lemma[1]=holiday	O[1]=1	pos[1]=NNS	lemma[-2]=start	O[-2]=1	pos[-2]=VBZ	lemma[2]=so	O[2]=1	pos[2]=RB
	lemma=holiday	O=1	pos=NNS	lemma[-1]=week	T[-1]=1	pos[-1]=NN	lemma[1]=so	O[1]=1	pos[1]=RB	lemma[-2]=next	M[-2]=1	pos[-2]=JJ	lemma[2]=short	O[2]=1	pos[2]=JJ
	lemma=so	O=1	pos=RB	lemma[-1]=holiday	O[-1]=1	pos[-1]=NNS	lemma[1]=short	O[1]=1	pos[1]=JJ	lemma[-2]=week	T[-2]=1	pos[-2]=NN
	lemma=short	O=1	pos=JJ	lemma[-1]=so	O[-1]=1	pos[-1]=RB	lemma[-2]=holiday	O[-2]=1	pos[-2]=NNS	__EOS__
