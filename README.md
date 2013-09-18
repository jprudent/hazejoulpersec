# hazejoulpersec

A small exercise written in clojure, for fun

## Usage

    -i input-file  ;; file or URL, see test/hazejoulpersec/testfile.txt, default to standard input
    -o output-file ;; default to standard output

exemples

    java -jar target/hazejoulpersec-0.1.0-SNAPSHOT-standalone.jar -i https://raw.github.com/jprudent/hazejoulpersec/master/test/hazejoulpersec/testfile.txt
    java -jar target/hazejoulpersec-0.1.0-SNAPSHOT-standalone.jar -i test/hazejoulpersec/testfile.txt -o /tmp/gop2
    java -jar target/hazejoulpersec-0.1.0-SNAPSHOT-standalone.jar < test/hazejoulpersec/testfile.txt

## License

Public Domain
