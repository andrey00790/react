https://github.com/tsenart/vegeta


vegeta attack -rate=50/s -duration=30s -targets=targets.txt | vegeta encode | \
                                                                jaggr @count=rps \
                                                                      hist\[100,200,300,400,500\]:code \
                                                                      p25,p50,p95:latency \
                                                                      sum:bytes_in \
                                                                      sum:bytes_out | \
                                                                jplot rps+code.hist.100+code.hist.200+code.hist.300+code.hist.400+code.hist.500 \
                                                                      latency.p95+latency.p50+latency.p25 \
                                                                      bytes_in.sum+bytes_out.sum
vegeta attack -rate=50/s -duration=30s -targets=targets.txt | vegeta report

vegeta report -inputs=results.bin -reporter=plot > plot.html

https://github.com/tsenart/vegeta