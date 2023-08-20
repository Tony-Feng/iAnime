package com.project.ianime.api

/**
 * Handles specific exception such as 401, 404 and 5xx errors
 */
class UnauthorizedException(message: String?) : Throwable(message)

class NotFoundException(message: String?) : Throwable(message)

class ConnectionException(message: String?) : Throwable(message)
