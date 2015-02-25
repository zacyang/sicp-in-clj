(ns sicp-in-clj.rational-test
  (:require [clojure.test :refer :all]
            [sicp-in-clj.rational :refer :all]))

(deftest basic-rational-structure-test
  (testing "should be able to construct a rational number"
    (is (make-rat 1 2) )))
