(ns guard-gallivant
  (:require [clojure.core.matrix :as m]
            [clojure.string :as s]
            [clojure.pprint :refer [pprint]]))

(defn parse [file]
  (->> (slurp file)
       (s/split-lines)
       (map vec)
       (m/mutable)))

(defn find-guard [grid]
  (first (for [y (range (m/row-count grid))
               x (range (m/column-count grid))
               :when (= \^ (m/mget grid y x))]
           [y x])))

; (def grid (parse "example.txt"))
(def grid (parse "puzzle-input.txt"))
(def guard (find-guard grid))

(defn in-bounds? [matrix [y x]]
  (and (>= y 0) (< y (m/row-count matrix))
       (>= x 0) (< x (m/column-count matrix))))

(defn turn [heading]
  (let [north [-1 0]
        east [0 1]
        south [1 0]
        west [0 -1]]
    (cond
      (= heading north) east
      (= heading east) south
      (= heading south) west
      (= heading west) north)))

(def directions {[-1 0] :north
                 [0 1] :east
                 [1 0] :south
                 [0 -1] :west})

(defn cast-ray! [grid guard direction seen-so-far]
  (loop [[y x] guard
         seen seen-so-far]
    (let [heading (directions direction)
          state [[y x] heading]]
      (cond
        (contains? seen state)
        {:loop? true :seen seen :continue? false}
        (not (in-bounds? grid [y x]))
        {:seen seen
         :continue? false :last-pos (m/sub [y x] direction)}
        (= \# (m/mget grid y x))
        {:continue? true :last-pos (m/sub [y x] direction)
         :new-heading (turn direction)
         :seen seen}
        :else
        (do
          (m/mset! grid y x \X)
          (recur (m/add [y x] direction)
                 (conj seen state)))))))

(defn walk! [grid]
  (loop [pos (find-guard grid)
         heading [-1 0]
         seen #{}]
    (let [path (cast-ray! grid pos heading seen)]
      (if (path :continue?)
        (recur (path :last-pos)
               (path :new-heading)
               (into seen (path :seen)))
        path))))

(m/pm grid)

(defn makes-loop? [grid [y x]]
  (let [test-grid (m/clone grid)]
    (m/mset! test-grid y x \#)
    (:loop? (walk! test-grid))))

(defn trajectory [grid]
  (let [test-grid (m/clone grid)
        guard (find-guard test-grid)
        visited (set (map first (:seen (walk! test-grid))))]
    (disj visited guard)))

(def candidate-obstacles (trajectory grid))

(pprint (count (remove nil? (map #(makes-loop? grid %) candidate-obstacles))))
