(ns postie.geographies.ca-test
  (:require [clojure.test :refer [deftest is testing]]
            [postie.geographies.ca :as sut]
            [malli.generator :as g]))

(deftest valid-ca-postcode?-test
  (testing "when given a valid postcode"
    (is (true? (sut/valid? "K1A 0A1"))))

  (testing "it handles badly formatted but valid postcodes"
    (let [valid? (sut/valid? "k1a0a1")]
      (is (true? valid?))))

  (testing "it handles lower case postcodes"
    (is (true? (sut/valid? "k1a 0a1"))))

  (testing "when given an invalid postcode"
    (is (false? (sut/valid? "KAA 01A")))))

(deftest format-ca-postcode-test
  (testing "It returns the formatted postcode when it is valid"
    (is (= "K1A 0A1"
          (sut/format-postcode "k1a 0A1"))))
  (testing "It handles badly formatted postcodes"
    (is (= "K1A 0A1"
           (sut/format-postcode "k1a0a1")))))

(deftest generate-ca-postcode-test
  (testing "geneates a valid postcode"
    (is (sut/valid? (g/generate sut/generator)))))
