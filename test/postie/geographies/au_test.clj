(ns postie.geographies.au-test
  (:require [clojure.test :refer [deftest testing is]]
            [postie.geographies.au :as sut]
            [malli.generator :as g]))

(deftest valid-test
  (testing "validates real postcodes"
    (is (true? (sut/valid? "2000"))))
  (testing "does not validate invalid postcodes"
    (is (false? (sut/valid? "200a")))))

(deftest format-postcode-test
  (testing "does nothing for valid postcodes"
    (is (= "2000"
           (sut/format-postcode "2000"))))

  (testing "returns false for invalid postcodes"
    (is (false? (sut/format-postcode "200a")))))

( deftest generator-test
  (testing "it generates a valid postcode"
    (is (sut/valid? (g/generate sut/generator)))))
