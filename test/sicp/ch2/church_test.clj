(ns sicp.ch2.church-test
  (:use [clojure.test :refer :all]
   [sicp.ch2.church :refer :all]))
(def church-100 (reduce church-plus (take 100 (repeat one))))

(deftest church-2-int-coverting
         (testing "covert church to in"
                  (is (= (church->int church-zero) 0))
                  (is (= (church->int one) 1))
                  (is (= (church->int two) 2))
                  (is (= (church->int church-100) 100))
                  ))


(deftest int-2-church-coverting
         (testing "covert int to church"
                  (is (= (int->church 0) church-zero))
                  (is (= (int->church 1) one))
                  (is (= (int->church 2) two))
                  (is (= (int->church 100) church-100))
                  ))
