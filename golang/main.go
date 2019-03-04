package main

import (
	"flag"
	"net/http"

	"golang.org/x/net/context"
	"google.golang.org/grpc"

	"github.com/golang/glog"
	"github.com/grpc-ecosystem/grpc-gateway/runtime"

	gw "./helloworld"
)

var (
	echoEndpoint = flag.String("echo_endpoint", "localhost:8080", "endpoint of grpc gateway.")
)

func run() error {
	ctx := context.Background()
	ctx, cancel := context.WithCancel(ctx)
	defer cancel()

	mux := runtime.NewServeMux()
	opts := []grpc.DialOption{grpc.WithInsecure()}
	err := gw.RegisterGreeterHandlerFromEndpoint(ctx, mux, *echoEndpoint, opts)
	if err != nil {
		return err
	}

	return http.ListenAndServe(":9090", mux)
}

func main() {
	flag.Parse()
	defer glog.Flush()

	if err := run(); err != nil {
		glog.Fatal(err)
	}
}
