(ns hazejoulpersec.core-test
  (:require [clojure.test :refer :all]
            [hazejoulpersec.core :refer :all]))

(def coors {:a [0 0]
             :b [0 100]
             :c [100 100]
             :d [100 0]} )

(def problems (list {:a 1 :b 2 :c 2}
                   {:b 11 :c 5 :d 3}))

(deftest a-test
  (testing "problems"
    (is (= (list (list 40 80) (list 42 84)) (barycentres problems coors)))))

(deftest input
  (is (= {:coordinates {:D [100 0], :C [100 100], :B [0 100], :A [0 0]}
          :problems [{:A 1, :B 2, :C 2} {:B 11, :C 5, :D 3}]}
  (read-problems "test/hazejoulpersec/testfile.txt"))))

(deftest output
  (is (= "40 80\n42 84\n"
         (do
           (read-and-solve
             "test/hazejoulpersec/testfile.txt"
             "/tmp/foo")
           (slurp "/tmp/foo")))))
