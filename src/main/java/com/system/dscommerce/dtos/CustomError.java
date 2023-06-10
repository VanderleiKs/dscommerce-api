package com.system.dscommerce.dtos;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public record CustomError(
                Instant timestamp,
                Integer status,
                String error,
                String path,

                @JsonInclude(JsonInclude.Include.NON_NULL) List<FieldMessage> errors) {

        public CustomError(Instant timestamp, Integer status, String error, String path) {
                this(timestamp, status, error, path, null);
        }

        public CustomError(Instant timestamp, Integer status, String error, String path,
                        List<FieldMessage> errors) {
                this.timestamp = timestamp;
                this.status = status;
                this.error = error;
                this.path = path;
                this.errors = errors;

        }

}
