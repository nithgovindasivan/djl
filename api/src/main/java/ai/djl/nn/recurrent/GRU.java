/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.nn.recurrent;

import ai.djl.nn.Block;

/**
 * Applies GRU (Gated Recurrent Unit) recurrent layer to input.
 *
 * <p>Reference paper - Gated Recurrent Unit - Cho et al. 2014. http://arxiv.org/abs/1406.1078. The
 * definition of GRU here is slightly different from the paper but compatible with CUDNN.
 *
 * <p>$$ \begin{split}\begin{array}{ll} r_t = \mathrm{sigmoid}(W_{ir} x_t + b_{ir} + W_{hr}
 * h_{(t-1)} + b_{hr}) \\ z_t = \mathrm{sigmoid}(W_{iz} x_t + b_{iz} + W_{hz} h_{(t-1)} + b_{hz}) \\
 * n_t = \tanh(W_{in} x_t + b_{in} + r_t * (W_{hn} h_{(t-1)}+ b_{hn})) \\ h_t = (1 - z_t) * n_t +
 * z_t * h_{(t-1)} \\ \end{array}\end{split} $$
 */
public class GRU extends RecurrentCell {

    GRU(Builder builder) {
        super(builder);
        currentVersion = 1;
        mode = "gru";
        gates = 3;
    }

    /** The Builder to construct a {@link GRU} type of {@link Block}. */
    public static final class Builder extends BaseBuilder<Builder> {

        /** {@inheritDoc} */
        @Override
        protected Builder self() {
            return this;
        }

        /**
         * Builds a {@link GRU} block.
         *
         * @return the {@link GRU} block
         */
        public GRU build() {
            if (stateSize == -1 || numStackedLayers == -1) {
                throw new IllegalArgumentException("Must set stateSize and numStackedLayers");
            }
            return new GRU(this);
        }
    }
}
