	lemma=steamboat	O=1	pos=NN	lemma[1]=dinner	O[1]=1	pos[1]=NN	lemma[2]=tn	O[2]=1	pos[2]=NN	__BOS__
	lemma=dinner	O=1	pos=NN	lemma[-1]=steamboat	O[-1]=1	pos[-1]=NN	lemma[1]=tn	O[1]=1	pos[1]=NN	lemma[2]=again	O[2]=1	pos[2]=RB
	lemma=tn	O=1	pos=NN	lemma[-1]=dinner	O[-1]=1	pos[-1]=NN	lemma[1]=again	O[1]=1	pos[1]=RB	lemma[-2]=steamboat	O[-2]=1	pos[-2]=NN	lemma[2]=!	O[2]=1	pos[2]=.
	lemma=again	O=1	pos=RB	lemma[-1]=tn	O[-1]=1	pos[-1]=NN	lemma[1]=!	O[1]=1	pos[1]=.	lemma[-2]=dinner	O[-2]=1	pos[-2]=NN	lemma[2]=fri	T[2]=1	pos[2]=SYM
	lemma=!	O=1	pos=.	lemma[-1]=again	O[-1]=1	pos[-1]=RB	lemma[1]=fri	T[1]=1	pos[1]=SYM	lemma[-2]=tn	O[-2]=1	pos[-2]=NN	lemma[2]=-	M[2]=1	pos[2]=:
	lemma=fri	T=1	pos=SYM	lemma[-1]=!	O[-1]=1	pos[-1]=.	lemma[1]=-	M[1]=1	pos[1]=:	lemma[-2]=again	O[-2]=1	pos[-2]=RB	lemma[2]=IM	O[2]=1	pos[2]=NNP
	lemma=-	M=1	RECORD=1	pos=:	lemma[-1]=fri	T[-1]=1	pos[-1]=SYM	lemma[1]=IM	O[1]=1	pos[1]=NNP	lemma[-2]=!	O[-2]=1	pos[-2]=.	lemma[2]=KIM	O[2]=1	pos[2]=NNP
	lemma=IM	O=1	pos=NNP	lemma[-1]=-	M[-1]=1	pos[-1]=:	lemma[1]=KIM	O[1]=1	pos[1]=NNP	lemma[-2]=fri	T[-2]=1	pos[-2]=SYM	lemma[2]=sit	T[2]=1	pos[2]=VBD
	lemma=KIM	O=1	pos=NNP	lemma[-1]=IM	O[-1]=1	pos[-1]=NNP	lemma[1]=sit	T[1]=1	pos[1]=VBD	lemma[-2]=-	M[-2]=1	pos[-2]=:	lemma[2]=-	M[2]=1	pos[2]=:
	lemma=sit	T=1	pos=VBD	lemma[-1]=KIM	O[-1]=1	pos[-1]=NNP	lemma[1]=-	M[1]=1	pos[1]=:	lemma[-2]=IM	O[-2]=1	pos[-2]=NNP	lemma[2]=Mookata	O[2]=1	pos[2]=NNP
	lemma=-	M=1	RECORD=1	pos=:	lemma[-1]=sit	T[-1]=1	pos[-1]=VBD	lemma[1]=Mookata	O[1]=1	pos[1]=NNP	lemma[-2]=KIM	O[-2]=1	pos[-2]=NNP	lemma[2]=sun	T[2]=1	pos[2]=NN
	lemma=Mookata	O=1	pos=NNP	lemma[-1]=-	M[-1]=1	pos[-1]=:	lemma[1]=sun	T[1]=1	pos[1]=NN	lemma[-2]=sit	T[-2]=1	pos[-2]=VBD	lemma[2]=-	M[2]=1	pos[2]=:
	lemma=sun	T=1	pos=NN	lemma[-1]=Mookata	O[-1]=1	pos[-1]=NNP	lemma[1]=-	M[1]=1	pos[1]=:	lemma[-2]=-	M[-2]=1	pos[-2]=:	lemma[2]=Hai	O[2]=1	pos[2]=NNP
	lemma=-	M=1	RECORD=1	pos=:	lemma[-1]=sun	T[-1]=1	pos[-1]=NN	lemma[1]=Hai	O[1]=1	pos[1]=NNP	lemma[-2]=Mookata	O[-2]=1	pos[-2]=NNP	lemma[2]=di	O[2]=1	pos[2]=FW
	lemma=Hai	O=1	pos=NNP	lemma[-1]=-	M[-1]=1	pos[-1]=:	lemma[1]=di	O[1]=1	pos[1]=FW	lemma[-2]=sun	T[-2]=1	pos[-2]=NN	lemma[2]=lao	O[2]=1	pos[2]=FW
	lemma=di	O=1	pos=FW	lemma[-1]=Hai	O[-1]=1	pos[-1]=NNP	lemma[1]=lao	O[1]=1	pos[1]=FW	lemma[-2]=-	M[-2]=1	pos[-2]=:	lemma[2]=become	O[2]=1	pos[2]=VBG
	lemma=lao	O=1	pos=FW	lemma[-1]=di	O[-1]=1	pos[-1]=FW	lemma[1]=become	O[1]=1	pos[1]=VBG	lemma[-2]=Hai	O[-2]=1	pos[-2]=NNP	lemma[2]=fatter	O[2]=1	pos[2]=JJR
	lemma=become	O=1	pos=VBG	lemma[-1]=lao	O[-1]=1	pos[-1]=FW	lemma[1]=fatter	O[1]=1	pos[1]=JJR	lemma[-2]=di	O[-2]=1	pos[-2]=FW	lemma[2]=alr	O[2]=1	pos[2]=NN
	lemma=fatter	O=1	pos=JJR	lemma[-1]=become	O[-1]=1	pos[-1]=VBG	lemma[1]=alr	O[1]=1	pos[1]=NN	lemma[-2]=lao	O[-2]=1	pos[-2]=FW	lemma[2]=leh	O[2]=1	pos[2]=NN
	lemma=alr	O=1	pos=NN	lemma[-1]=fatter	O[-1]=1	pos[-1]=JJR	lemma[1]=leh	O[1]=1	pos[1]=NN	lemma[-2]=become	O[-2]=1	pos[-2]=VBG
	lemma=leh	O=1	pos=NN	lemma[-1]=alr	O[-1]=1	pos[-1]=NN	lemma[-2]=fatter	O[-2]=1	pos[-2]=JJR	__EOS__
