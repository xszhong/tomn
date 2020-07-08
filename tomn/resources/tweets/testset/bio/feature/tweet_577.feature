	lemma=NUMERAL	N=1	RECORD=1	pos=CD	lemma[1]=hour	T[1]=1	pos[1]=NNS	lemma[2]=go	O[2]=1	pos[2]=VBG	__BOS__
	lemma=hour	T=1	pos=NNS	lemma[-1]=NUMERAL	N[-1]=1	pos[-1]=CD	lemma[1]=go	O[1]=1	pos[1]=VBG	lemma[2]=NUMERAL	N[2]=1	pos[2]=CD
	lemma=go	O=1	pos=VBG	lemma[-1]=hour	T[-1]=1	pos[-1]=NNS	lemma[1]=NUMERAL	N[1]=1	pos[1]=CD	lemma[-2]=NUMERAL	N[-2]=1	pos[-2]=CD	lemma[2]=hour	T[2]=1	pos[2]=NNS
	lemma=NUMERAL	N=1	RECORD=1	pos=CD	lemma[-1]=go	O[-1]=1	pos[-1]=VBG	lemma[1]=hour	T[1]=1	pos[1]=NNS	lemma[-2]=hour	T[-2]=1	pos[-2]=NNS	lemma[2]=movie	O[2]=1	pos[2]=NN
	lemma=hour	T=1	pos=NNS	lemma[-1]=NUMERAL	N[-1]=1	pos[-1]=CD	lemma[1]=movie	O[1]=1	pos[1]=NN	lemma[-2]=go	O[-2]=1	pos[-2]=VBG	lemma[2]=wtf	O[2]=1	pos[2]=NN
	lemma=movie	O=1	pos=NN	lemma[-1]=hour	T[-1]=1	pos[-1]=NNS	lemma[1]=wtf	O[1]=1	pos[1]=NN	lemma[-2]=NUMERAL	N[-2]=1	pos[-2]=CD	lemma[2]=whole	M[2]=1	pos[2]=JJ
	lemma=wtf	O=1	pos=NN	lemma[-1]=movie	O[-1]=1	pos[-1]=NN	lemma[1]=whole	M[1]=1	pos[1]=JJ	lemma[-2]=hour	T[-2]=1	pos[-2]=NNS	lemma[2]=body	O[2]=1	pos[2]=NN
	lemma=whole	M=1	pos=JJ	lemma[-1]=wtf	O[-1]=1	pos[-1]=NN	lemma[1]=body	O[1]=1	pos[1]=NN	lemma[-2]=movie	O[-2]=1	pos[-2]=NN	lemma[2]=pain	O[2]=1	pos[2]=NN
	lemma=body	O=1	pos=NN	lemma[-1]=whole	M[-1]=1	pos[-1]=JJ	lemma[1]=pain	O[1]=1	pos[1]=NN	lemma[-2]=wtf	O[-2]=1	pos[-2]=NN
	lemma=pain	O=1	pos=NN	lemma[-1]=body	O[-1]=1	pos[-1]=NN	lemma[-2]=whole	M[-2]=1	pos[-2]=JJ	__EOS__
