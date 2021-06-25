package br.com.rchlo.store.controller;

import br.com.rchlo.store.domain.Payment;
import br.com.rchlo.store.dto.PaymentDto;
import br.com.rchlo.store.dto.form.PaymentForm;
import br.com.rchlo.store.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private PaymentRepository paymentRepository;
	
	public PaymentController(final PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> readById(@PathVariable Long id) {
		final Payment payment = this.paymentRepository
				                        .findById(id)
				                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return ResponseEntity.ok(new PaymentDto(payment));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentForm form,
	                                         UriComponentsBuilder uriBuilder) {
		final Payment payment = form.convert();
		this.paymentRepository.save(payment);
		
		final URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PaymentDto(payment));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDto> confirm(
			@PathVariable Long id) {
		final Optional<Payment> paymentOptional = this.paymentRepository.findById(id);
		
		if (paymentOptional.isPresent()) {
			final Payment payment = paymentOptional.get();
			payment.confirm();
			
			return ResponseEntity.ok(new PaymentDto(payment));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<PaymentDto> cancel(@PathVariable Long id) {
		final Optional<Payment> paymentOptional = this.paymentRepository.findById(id);
		
		if (paymentOptional.isPresent()) {
			final Payment payment = paymentOptional.get();
			payment.cancel();
			
			return ResponseEntity.ok(new PaymentDto(payment));
		}
		
		return ResponseEntity.notFound().build();
	}
}
