(use 'clojure.java.io)

(defn- to-problem [p1 w1 p2 w2 p3 w3]
  {(keyword p1) (Integer. w1) (keyword p2) (Integer. w2) (keyword p3) (Integer. w3)})

(defn read-problems

  ([filename]
  (with-open [rdr (reader filename)]
    (read-problems (line-seq rdr) {} [])))

  ([[line & more-lines] coors problems]
    (if (nil? line)
        {:coordinates coors :problems problems}
        (if-let [ [_ point x y] (re-matches #"(\w+) (\d+) (\d+)" line) ]
          (recur more-lines (assoc coors (keyword point) [(Integer. x) (Integer. y)]) problems)
          (if-let [[_ p1 w1 p2 w2 p3 w3] (re-matches #"(\w+) (\d+) (\w+) (\d+) (\w+) (\d+)" line)]
            (recur more-lines coors (conj problems (to-problem p1 w1 p2 w2 p3 w3)))
            (recur more-lines coors problems))))))

(defn write-solution [solution output]
  (spit output
        (clojure.string/join "\n"
                             (map #(clojure.string/join " " %) solution))))

(write-solution (list (list 40 80) (list 42 84)) "/tmp/goo")