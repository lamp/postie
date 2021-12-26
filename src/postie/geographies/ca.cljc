(ns postie.geographies.ca
  (:require [malli.generator :as g]
            [clojure.string :as string]
            [postie.geographies.common :refer [preprocess]]))

(def patterns {:fsa #"[A-CEGHJK-NPR-TVXY][0-9][A-CEGHJK-NPR-TV-Z]"
               :lsa #"[0-9][A-CEGHJK-NPR-TV-Z][0-9]"
               :complete #"^[A-CEGHJK-NPR-TVXY][0-9][A-CEGHJK-NPR-TV-Z] [0-9][A-CEGHJK-NPR-TV-Z][0-9]$"})

(def preprocess-ca
  (comp (fn [postcode]
          (let [thing (clojure.string/upper-case postcode)]
            [(subs thing 0 3) (subs thing 3)]))
        preprocess))

(defn valid?
  ([postcode]
   (->> postcode
       preprocess-ca
       (apply valid?)))
  ([fsa* lsa*]
   (let [{:keys [lsa fsa]} patterns]
     (some? (and (re-matches lsa lsa*)
                 (re-matches fsa fsa*))))))

(defn format-postcode [postcode]
  (let [[fsa lsa] (preprocess-ca postcode)]
    (if (valid? fsa lsa)
      (clojure.string/join " " [fsa lsa])
      false)))

(def generator
  (g/generator [:re (get patterns :complete)]))

(def strategy
  {:generator generator
   :formatter format-postcode
   :valid? valid?})
