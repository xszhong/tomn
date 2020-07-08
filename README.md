# TOMN
This repository includes the source codes, shell scripts, and datasets of our WWW2018 paper indicated below. The source codes are written in Java and are used for implementing our experiments. The shell scripts are used for running the Java codes. The datasets contains the original data and immediate data files.

These source codes, shell scripts, and datasets are uploaded directly from a Java project that is exported from Eclipse, therefore, they can be imported directly to Eclipse.

After importing the project, you need to import the Stanford CoreNLP model before you run it. In our implementation, we use the CoreNLP model in its 3.6.0 version that can be got from [the CoreNLP website](https://stanfordnlp.github.io/CoreNLP/history.html). The CoreNLP model is packaged into a ".jar" file and it therefore can be treated as a common ".jar" file.

To get started quickly, please go to the "command" folder, where the shell scripts are stored. First of all, in the file "project_setting.bsh," set the attribute "WORKHOME=/Users/Mathew/Java/projects"  to the project path in your workstation. Secondly, run the script "code_compile.bsh" to compile the java source codes. After that, run the script "tomn_jcrfsuite.bsh" to running experiments.

### Notes
For the experiments reported in our paper, the CRF framework we used is [Naoaki Okazaki's CRFSuite](http://www.chokkan.org/software/crfsuite/). But in these uploaded codes, we change to [a Java version of CRFSuite](https://github.com/vinhkhuc/jcrfsuite) for the convenience to anyone who is interested in our project.

### Publication
Xiaoshi Zhong and Erik Cambria. Time Expression Recognition Using a Constituent-based Tagging Scheme. In *Proceedings of the 2018 World Wide Web Conference*, pages 983-992, Lyon, France, 2018. [[pdf](https://xszhong.github.io/publications/TOMN-WWW2018-Zhong&Cambria.pdf)][[slides](https://xszhong.github.io/slides/TOMN-WWW2018-Zhong&Cambria-Slides.pdf)]

