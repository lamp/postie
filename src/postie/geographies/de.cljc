(ns postie.geographies.de
  (:require [postie.geographies.common :as c]
            [malli.generator :as g]))

(def pattern #"^([0]{1}[1-9]{1}|[1-9]{1}[0-9]{1})[0-9]{3}$")

(defn valid? [postcode]
  (->> postcode
       c/preprocess
       (re-matches pattern)
       some?))

(def format-postcode (partial c/default-formatter valid?))

(def generator
  (g/generator [:re pattern]))

(def strategy  {:valid? valid?
                :formatter format-postcode
                :generator generator})


