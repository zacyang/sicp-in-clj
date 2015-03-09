(ns sicp.ch2.round-test
  (require [clojure.test :refer :all]
           [sicp.ch2.round :refer :all]))

(deftest basic-round-test
  (testing ""
    (is (= (lower-bound (make-interval 1 2)) 1))
    (is (= (upper-bound (make-interval 1 2)) 2))
    ))
