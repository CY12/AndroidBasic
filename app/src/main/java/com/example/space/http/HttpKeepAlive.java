package com.example.space.http;

/**
 * HTTP是一个无状态的协议。
 * HTTP协议永远都是客户端发起请求，服务器回送响应。每次连接只处理一个请求,请求完关闭连接。
 * HTTP协议运行在TCP协议之上，它无状态会导致客户端的每次请求都需要重新建立TCP连接，频繁的连接，断开有性能损耗
 *
 * TCP keepalive（TCP的保活机制）
 * 如果在一段时间（保活时间：tcp_keepalive_time）内此连接都不活跃，开启保活功能的一端会向对端发送一个保活探测报文。
 *
 * 若对端正常存活，且连接有效，对端必然能收到探测报文并进行响应。此时，发送端收到响应报文则证明TCP连接正常，重置保活时间计数器即可。
 * 若由于网络原因或其他原因导致，发送端无法正常收到保活探测报文的响应。那么在一定**探测时间间隔（tcp_keepalive_intvl）后，
 * 将继续发送保活探测报文。直到收到对端的响应，或者达到配置的探测循环次数上限（tcp_keepalive_probes）
 * 都没有收到对端响应，这时对端会被认为不可达，TCP连接随存在但已失效，需要将连接做中断处理。
 *
 *
 * 对方主机仍在工作，并且可达	----> TCP连接正常，将保活计时器重置。
 * 对方主机已崩溃，包括：已关闭或者正在重启 ---->	TCP连接不正常，经过指定次数的探测依然没得到响应，则断开连接
 * 对方主机崩溃并且已经重启 ---->	重启后原连接已失效，对方由于不认识探测报文，会响应重置报文段，请求端将连接断开
 * 对方主机仍在工作，但由于某些原因不可达（如：网络原因） ----> 	TCP连接不正常，经过指定次数的探测依然没得到响应，则断开连接
 *
 * Http keep-alive
 * 若开启后，在一次http请求中，服务器进行响应后，不再直接断开TCP连接，而是将TCP连接维持一段时间。在这段时间内，
 * 如果同一客户端再次向服务端发起http请求，便可以复用此TCP连接，向服务端发起请求，并重置timeout时间计数器，在接下来一段时间内还可以继续复用。
 * 这样无疑省略了反复创建和销毁TCP连接的损耗
 *
 * http 会断开tcp连接 ，http保活就是让tcp不断开，具体执行保护的时tcp的保活探测报文
 */

public class HttpKeepAlive {
}
