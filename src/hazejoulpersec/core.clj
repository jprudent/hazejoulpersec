(ns hazejoulpersec.core)

(load "barycentre")
(load "io")

(defn read-and-solve [input-file output-file]
  (let [{:keys [problems coordinates]} (read-problems input-file)
        solution (barycentres problems coordinates)]
    (write-solution solution output-file)))
