(ns hazejoulpersec.core
   (:use [clojure.tools.cli :only (cli)])
  (:gen-class :main true))


;;; BARYCENTRES

(defn- product [coordinates [point weight] dim]
  (* ((point coordinates) dim) weight))

(defn- barycentre

  ([problem coordinates]
   (map #(barycentre problem coordinates %) [0 1]))

  ([problem coordinates dim]
    (int (/
      (apply + (map #(product coordinates % dim) problem))
      (apply + (vals problem))))))

(defn barycentres [problems coordinates]
  (map #(barycentre % coordinates) problems))


;;; READING AND WRITING

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
        (str (clojure.string/join "\n"
                             (map #(clojure.string/join " " %) solution)) "\n")))


;;; PUTING IT ALL TOGETHER

(defn read-and-solve [input-file output-file]
  (let [{:keys [problems coordinates]} (read-problems input-file)
        solution (barycentres problems coordinates)]
    (write-solution solution output-file)))

(defn -main
  [& args]
  (let [[opts _ _] (cli args
                  "Calcul de barycentres"
                  ["-i" "--input-file" :default *in*]
                  ["-o" "--output-file" :default *out*])]
      (read-and-solve (:input-file opts) (:output-file opts))))